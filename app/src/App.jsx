import React from 'react';
import './App.css';
import Home from './Home.jsx';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import GroupList from './GroupList.jsx';
import GroupEdit from "./GroupEdit.jsx";

const App = () => {
    return (
        <Router>
            <Routes>
                <Route exact path="/" element={<Home/>}/>
                <Route path='/groups' exact={true} element={<GroupList/>}/>
                <Route path='/groups/:id' element={<GroupEdit/>}/>
            </Routes>
        </Router>
    )
}

export default App;