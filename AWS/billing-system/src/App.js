// import React, { useState } from "react";
// import axios from "axios";
// import ImageZoomApp from "./Image";
// import Gallery from "./Components/gallery";
// import ImageGalleryLightbox from "./Image";

// const App = () => {


//   return (
//     <ImageGalleryLightbox/>
//   );
// };

// export default App;
import { useState } from 'react';

function App() {
  const [message, setMessage] = useState('');
  const [response, setResponse] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const apiUrl = ' https://czhpwxsdpi.execute-api.eu-north-1.amazonaws.com/default/PublishToSNSFunction';

    try {
      const res = await fetch(apiUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ message })
      });

      const data = await res.json();
      setResponse(`Message sent successfully! ID: ${data.messageId}`);
    } catch (err) {
      setResponse('Error sending message.');
      console.error(err);
    }
  };

  return (
    <div>
      <h2>Send a Message to AWS Lambda</h2>
      <form onSubmit={handleSubmit}>
        <textarea value={message} onChange={(e) => setMessage(e.target.value)} />
        <button type="submit">Send Message</button>
      </form>
      {response && <p>{response}</p>}
    </div>
  );
}

export default App;
