const WebSocket = require('ws');
const mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/employeeDB', {
    useNewUrlParser: true,
    useUnifiedTopology: true
}).then(() => console.log('Connected to MongoDB'))
  .catch(err => console.error('MongoDB connection error:', err));

const employeeSchema = new mongoose.Schema({
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number
});

const Employee = mongoose.model('Employee', employeeSchema);

const wss = new WebSocket.Server({ port: 8081 }, () => {
    console.log("WebSocket server running on ws://localhost:8080");
});

wss.on('connection', (ws) => {
    ws.send("Connected");
    console.log("Client connected");

    ws.on('message', async (message) => {
        console.log("Received:", message.toString());
        const parts = message.toString().split(' ');
        const command = parts[0];

        try {
            if (command === "INSERT" && parts.length === 6) {
                const [_, name, salary, role, department, experience] = parts;
                if (isNaN(salary) || isNaN(experience)) {
                    ws.send("Invalid salary or experience value.");
                    return;
                }
                const employee = new Employee({ name, salary: Number(salary), role, department, experience: Number(experience) });
                await employee.save();
                ws.send(`Employee inserted successfully.`);
            } else if (command === "RETRIEVE") {
                const employees = await Employee.find();
                if (employees.length === 0) {
                    ws.send("No employees found.");
                } else {
                    employees.forEach(emp => {
                        ws.send(`ID: ${emp._id}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`);
                    });
                }
            } else if (command === "RETRIEVE_BY_DEPT" && parts.length === 2) {
                const department = parts[1];
                const employees = await Employee.find({ department });
                if (employees.length === 0) {
                    ws.send("No employees found in this department.");
                } else {
                    employees.forEach(emp => {
                        ws.send(`ID: ${emp._id}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`);
                    });
                }
            } else {
                ws.send("Invalid command.");
            }
        } catch (error) {
            console.error("Error processing command:", error);
            ws.send("An error occurred while processing your request.");
        }
    });

    ws.on('close', () => {
        console.log("Client disconnected");
    });
});
