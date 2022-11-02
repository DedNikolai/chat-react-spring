import React, {Fragment, useContext} from 'react';
import {UserContext} from "./UserContext";
import {
    Navigate,
} from "react-router-dom";

function Login() {
    const auth = useContext(UserContext);
    if (auth.currentUser) {
        return <Navigate to="/" />
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        const data = new FormData(e.target);
        const request = {email: data.get('email'), password: data.get('password')};
        e.target.reset();
        fetch('/api/v1/auth/signin', {
            method: 'POST',
            body: JSON.stringify(request),
            headers: {
                'Content-Type': 'application/json'
            },
        })
            .then((res) => res.json())
            .then(result => auth.setToken(result.accessToken))
            .catch((err) => {
                console.log(err.message);
            });
    };

    return (
        <Fragment>
            <h1>Login</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <input autoComplete='email' type='text' name='email' placeholder='email'/>
                </div>
                <div>
                    <input autoComplete='current-password' type='password' name='password' placeholder='password'/>
                </div>
                <div>
                    <input type='submit' value='submit'/>
                </div>
            </form>
        </Fragment>

    )
}

export default Login;