import React, { useState } from 'react';
import Lightbox from './Lightbox';
// import sampleImage from '../assets/images/sample-image.jpg';
// import Lightbox from './Lightbox';
import dowload from "../assets/download.jpeg";
const images = [
  dowload
  // Add more images as needed
];

const Gallery = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [currentIndex, setCurrentIndex] = useState(0);

  const openLightbox = (index) => {
    setCurrentIndex(index);
    setIsOpen(true);
  };

  const closeLightbox = () => {
    setIsOpen(false);
  };

  const nextImage = () => {
    setCurrentIndex((prevIndex) => (prevIndex + 1) % images.length);
  };

  const prevImage = () => {
    setCurrentIndex((prevIndex) => (prevIndex - 1 + images.length) % images.length);
  };

  return (
    <div className="gallery">
      <div className="grid grid-cols-3 gap-4">
        {images.map((image, index) => (
          <img
            key={index}
            src={image}
            alt={`Gallery Image ${index + 1}`}
            className="cursor-pointer"
            onClick={() => openLightbox(index)}
          />
        ))}
      </div>
      {isOpen && (
        <Lightbox
          image={images[currentIndex]}
          onClose={closeLightbox}
          onNext={nextImage}
          onPrev={prevImage}
        />
      )}
    </div>
  );
};

export default Gallery;