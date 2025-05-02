import React, { useState } from "react";
import { Button } from "@/components/ui/button";

const ImageZoomApp = () => {
  const [scale, setScale] = useState(1);

  const zoomIn = () => setScale(prev => Math.min(prev + 0.1, 3));
  const zoomOut = () => setScale(prev => Math.max(prev - 0.1, 0.1));
  const resetZoom = () => setScale(1);

  return (
    <div className="flex flex-col items-center justify-center min-h-screen p-4 bg-gray-100">
      <div className="mb-4 space-x-2">
        <Button onClick={zoomIn}>Zoom In</Button>
        <Button onClick={zoomOut}>Zoom Out</Button>
        <Button onClick={resetZoom}>Reset</Button>
      </div>
      <div className="overflow-hidden border rounded-xl shadow-lg bg-white">
        <img
          src="https://via.placeholder.com/600x400.png?text=Sample+Image"
          alt="Zoomable"
          style={{ transform: `scale(${scale})`, transition: "transform 0.3s" }}
          className="origin-center"
        />
      </div>
    </div>
  );
};

export default ImageZoomApp;
