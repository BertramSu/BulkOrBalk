import React from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ItemList from './ItemList';
import ItemEdit from './ItemEdit';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route path='/items' exact={true} element={<ItemList/>}/>
        <Route path='/item/:id' element={<ItemEdit/>}/>
      </Routes>
    </Router>
  )
}

export default App;