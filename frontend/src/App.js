import React, {useState, useMemo, useEffect} from 'react';
import {Route, Routes,} from "react-router-dom";
import RequireAuth from './RequireAuth';
import Login from './Login';
import Home from './Home';
import {UserContext} from "./UserContext";

function App() {
  const [currentUser, setCurrentUser] = useState(null);
  const [token, setToken] = useState(null);
    const value = useMemo(
        () => ({ currentUser, token, setCurrentUser, setToken }),
        [currentUser, token]
    );

    useEffect(() => {
        fetch('api/v1/users/current',{
            method: 'GET',
            headers: {
                accept: 'application/json',
                'Authorization': `Bearer ${token}`
            },
        }).then(res => {
            res.json().then(res => {
                setCurrentUser(res)
            })
        })
    }, [token]);

  return (
      <UserContext.Provider value={value}>
          <Routes>
              <Route path="/login" element={<Login/>}/>
              <Route path="/"
                     element={
                         <RequireAuth>
                             <Home/>
                         </RequireAuth>
                     }
              />
          </Routes>
      </UserContext.Provider>
  );
}

export default App;
