import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ImageZoomApp from './Image'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <ImageZoomApp/>
    </>
  )
}

export default App
