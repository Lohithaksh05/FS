import React from 'react';

const Navigation = ({ currentIndex, totalImages, onPrev, onNext }) => {
  return (
    <div className="navigation">
      <button onClick={onPrev} disabled={currentIndex === 0} className="nav-button">
        &lt; Prev
      </button>
      <span className="image-counter">
        {currentIndex + 1} of {totalImages}
      </span>
      <button onClick={onNext} disabled={currentIndex === totalImages - 1} className="nav-button">
        Next &gt;
      </button>
    </div>
  );
};

export default Navigation;