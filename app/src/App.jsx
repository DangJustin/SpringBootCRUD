import React, { useState, useEffect } from 'react'
import viteLogo from '/vite.svg'
import './App.css'

const App = () => {
    const [groups, setGroups] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);
        fetch('api/groups')
            .then(response => {
                if (!response.ok) {
                    throw new Error("HTTP error " + response.status);
                }
                if (response.ok) {
                    console.log("Success");
                }
                return response.json();
            })
            .then(data => {
                setGroups(data);
                setLoading(false);
            })
    }, []);

    if (loading) {
        return <p>Loading...</p>;
    }

    return (
        <div className= "App">
            <header className = "App-header">
                <img src = {viteLogo} className="App-logo" alt="logo"/>
                <div className="App-intro">
                    <h2>Group List</h2>
                    {groups.map(group =>
                        <div key={group.id}>
                            {group.name}
                        </div>
                    )}
                </div>
            </header>
        </div>
    );

}

export default App
