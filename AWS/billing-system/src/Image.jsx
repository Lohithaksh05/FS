import { useState, useEffect } from 'react';
import { ChevronLeft, ChevronRight, ZoomIn, ZoomOut, X } from 'lucide-react';

// Inline styles approach for when Tailwind isn't loading properly
const styles = {
  container: {
    maxWidth: '1200px',
    margin: '0 auto',
    padding: '2rem',
    background: 'white',
    fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif'
  },
  heading: {
    fontSize: '1.8rem',
    fontWeight: 'bold',
    marginBottom: '1.5rem',
    display: 'flex',
    alignItems: 'center',
    color: '#333'
  },
  emoji: {
    marginRight: '0.5rem'
  },
  gallery: {
    display: 'grid',
    gridTemplateColumns: 'repeat(auto-fill, minmax(200px, 1fr))',
    gap: '1rem'
  },
  imageCard: {
    overflow: 'hidden',
    borderRadius: '8px',
    backgroundColor: '#f3f4f6',
    cursor: 'pointer',
    boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
    transition: 'all 0.3s ease',
    border: '1px solid #e5e7eb'
  },
  imageCardImg: {
    width: '100%',
    height: '200px',
    objectFit: 'cover',
    transition: 'transform 0.3s ease'
  },
  lightbox: {
    position: 'fixed',
    inset: 0,
    backgroundColor: 'rgba(0, 0, 0, 0.85)',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    zIndex: 9999
  },
  lightboxContent: {
    position: 'relative',
    width: '100%',
    height: '100%',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center'
  },
  closeButton: {
    position: 'absolute',
    top: '1rem',
    right: '1rem',
    color: 'white',
    padding: '0.5rem',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
    borderRadius: '50%',
    cursor: 'pointer',
    border: 'none'
  },
  title: {
    color: 'white',
    fontSize: '1.25rem',
    fontWeight: '600',
    marginBottom: '1rem'
  },
  imageContainer: {
    position: 'relative',
    flex: 1,
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    width: '100%',
    maxWidth: '1200px'
  },
  lightboxImage: {
    maxHeight: '80vh',
    maxWidth: '90%',
    objectFit: 'contain',
    transition: 'transform 0.3s ease'
  },
  navButton: {
    position: 'absolute',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
    padding: '0.5rem',
    borderRadius: '50%',
    color: 'white',
    border: 'none',
    cursor: 'pointer'
  },
  prevButton: {
    left: '1rem'
  },
  nextButton: {
    right: '1rem'
  },
  zoomControls: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    gap: '1rem',
    marginTop: '1rem',
    paddingBottom: '1rem'
  },
  zoomButton: {
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
    padding: '0.5rem',
    borderRadius: '50%',
    color: 'white',
    border: 'none',
    cursor: 'pointer'
  },
  zoomText: {
    color: 'white'
  },
  instructions: {
    position: 'absolute',
    bottom: '1rem',
    left: '1rem',
    color: 'white',
    fontSize: '0.875rem',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
    padding: '0.5rem',
    borderRadius: '0.25rem'
  }
};

export default function ImageGalleryLightbox() {
  const [selectedImage, setSelectedImage] = useState(null);
  const [zoomLevel, setZoomLevel] = useState(1);
  
  // Sample gallery images - you can replace these with your own
  const images = [
    {
      id: 1,
      src: require('./assets/1.jpeg'),
      alt: "Workspace with laptop",
      title: "Modern Workspace"
    },
    {
      id: 2,
      src: "/api/placeholder/400/250",
      alt: "Code symbols",
      title: "Code Symbols"
    },
    {
      id: 3,
      src: "/api/placeholder/400/250",
      alt: "Algorithm concept",
      title: "Algorithm Concept"
    },
    {
      id: 4,
      src: "/api/placeholder/400/250",
      alt: "Programmer coffee",
      title: "Programmer Coffee"
    },
    {
      id: 5,
      src: "/api/placeholder/400/250",
      alt: "Programmer's life",
      title: "Programmer's Life"
    },
    {
      id: 6,
      src: "/api/placeholder/400/250",
      alt: "Hacker concept",
      title: "Hacker Concept"
    },
    {
      id: 7,
      src: "/api/placeholder/400/250",
      alt: "Eat sleep create repeat",
      title: "Creative Process"
    },
    {
      id: 8,
      src: "/api/placeholder/400/250",
      alt: "Motivational quotes",
      title: "Motivational Quotes"
    },
    {
      id: 9,
      src: "/api/placeholder/400/250",
      alt: "Dream quote",
      title: "Dream Quote"
    },
    {
      id: 10,
      src: "/api/placeholder/400/250",
      alt: "Nothing is impossible",
      title: "Nothing is Impossible"
    }
  ];

  // Handle keyboard navigation
  useEffect(() => {
    const handleKeyDown = (e) => {
      if (!selectedImage) return;

      switch (e.key) {
        case 'ArrowLeft':
          navigateImages('prev');
          break;
        case 'ArrowRight':
          navigateImages('next');
          break;
        case 'ArrowUp':
          zoomIn();
          break;
        case 'ArrowDown':
          zoomOut();
          break;
        case 'Escape':
          closeLightbox();
          break;
        default:
          break;
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [selectedImage]);

  // Navigate between images
  const navigateImages = (direction) => {
    if (!selectedImage) return;
    
    const currentIndex = images.findIndex(img => img.id === selectedImage.id);
    let newIndex;
    
    if (direction === 'next') {
      newIndex = (currentIndex + 1) % images.length;
    } else {
      newIndex = (currentIndex - 1 + images.length) % images.length;
    }
    
    setSelectedImage(images[newIndex]);
    setZoomLevel(1); // Reset zoom when changing images
  };

  // Open the lightbox with selected image
  const openLightbox = (image) => {
    setSelectedImage(image);
    setZoomLevel(1);
  };

  // Close the lightbox
  const closeLightbox = () => {
    setSelectedImage(null);
    setZoomLevel(1);
  };

  // Zoom functions
  const zoomIn = () => {
    if (zoomLevel < 3) {
      setZoomLevel(prevZoom => prevZoom + 0.5);
    }
  };

  const zoomOut = () => {
    if (zoomLevel > 0.5) {
      setZoomLevel(prevZoom => prevZoom - 0.5);
    }
  };

  return (
    <div style={styles.container}>
      <h1 style={styles.heading}>
        <span style={styles.emoji}>🖼️</span> Image Gallery Lightbox with Navigation
      </h1>
      
      {/* Image Gallery Grid */}
      <div style={styles.gallery}>
        {images.map((image) => (
          <div 
            key={image.id} 
            style={styles.imageCard}
            onClick={() => openLightbox(image)}
          >
            <img 
              src={image.src} 
              alt={image.alt} 
              style={{
                ...styles.imageCardImg,
                transform: `scale(1)`
              }}
              onMouseOver={(e) => {
                e.currentTarget.style.transform = 'scale(1.05)';
              }}
              onMouseOut={(e) => {
                e.currentTarget.style.transform = 'scale(1)';
              }}
            />
          </div>
        ))}
      </div>

      {/* Lightbox */}
      {selectedImage && (
        <div style={styles.lightbox}>
          <div style={styles.lightboxContent}>
            {/* Close button */}
            <button 
              onClick={closeLightbox}
              style={styles.closeButton}
            >
              <X size={24} />
            </button>
            
            {/* Title */}
            <h2 style={styles.title}>
              {selectedImage.title}
            </h2>

            {/* Image container */}
            <div style={styles.imageContainer}>
              <img 
                src={selectedImage.src} 
                alt={selectedImage.alt} 
                style={{
                  ...styles.lightboxImage,
                  transform: `scale(${zoomLevel})`
                }}
              />
              
              {/* Navigation arrows */}
              <button 
                onClick={() => navigateImages('prev')} 
                style={{...styles.navButton, ...styles.prevButton}}
              >
                <ChevronLeft size={24} />
              </button>
              
              <button 
                onClick={() => navigateImages('next')} 
                style={{...styles.navButton, ...styles.nextButton}}
              >
                <ChevronRight size={24} />
              </button>
            </div>

            {/* Zoom controls */}
            <div style={styles.zoomControls}>
              <button 
                onClick={zoomOut} 
                style={styles.zoomButton}
                disabled={zoomLevel <= 0.5}
              >
                <ZoomOut size={20} />
              </button>
              <span style={styles.zoomText}>Zoom: {zoomLevel.toFixed(1)}x</span>
              <button 
                onClick={zoomIn} 
                style={styles.zoomButton}
                disabled={zoomLevel >= 3}
              >
                <ZoomIn size={20} />
              </button>
            </div>

            {/* Instructions */}
            <div style={styles.instructions}>
              <p>Use arrow keys: ← → to navigate, ↑ ↓ to zoom</p>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}