import React from 'react';
// import './Lightbox.css'; // Assuming you have some styles for the lightbox

const Lightbox = ({ isOpen, currentImage, images, onClose, onNext, onPrev }) => {
  if (!isOpen) return null;

  return (
    <div className="lightbox-overlay" onClick={onClose}>
      <div className="lightbox-content" onClick={e => e.stopPropagation()}>
        <img src={currentImage} alt="Lightbox" className="lightbox-image" />
        <div className="lightbox-navigation">
          <button onClick={onPrev} className="nav-button">❮</button>
          <button onClick={onNext} className="nav-button">❯</button>
        </div>
        <button onClick={onClose} className="close-button">✖</button>
      </div>
    </div>
  );
};

export default Lightbox;