import React, {useEffect, useState, useRef, useContext} from 'react';
import {UserContext} from "./UserContext";

let stompClient = null;

function Home() {
    const [messages, setMessages] = useState([]);
    const newMessage = useRef('');
    const effectCalled = useRef(false);
    const auth = useContext(UserContext);

    const send = () => {
        let msg = {text: newMessage.current.value};
        newMessage.current.value = '';
        fetch('http://localhost:8080/messages', {
            method: 'POST',
            body: JSON.stringify(msg),
            headers: {
                'Content-Type': 'application/json',
                'X-Authorization': `token ${auth.token}`
            }
        })
    };

    useEffect(() => {
        if (!effectCalled.current) {
            fetch('http://localhost:8080/messages',{
                method: 'GET',
                headers: {
                    accept: 'application/json',
                },
            }).then(response => response.json()).then(res => {
                setMessages(res)
            });
            connect();
            console.log('єффект');
            effectCalled.current = true;
        }
    }, []);

    const connect = () => {
        const Stomp = require("stompjs");
        let SockJS = require("sockjs-client");
        SockJS = new SockJS("http://localhost:8080/ws");
        stompClient = Stomp.over(SockJS);
        console.log(auth.token)
        stompClient.connect({'X-Authorization': `token ${auth.token}`}, onConnected, onError);
    };

    const onConnected = () => {
        console.log("connected");
        stompClient.subscribe(
            "/topic/messages",
            onMessageReceived,
            {'X-Authorization': `token ${auth.token}`}
        );
    };

    const onError = (err) => {
        console.log(err);
    };

    const onMessageReceived = (msg) => {
        console.log('получно')
        let newMsg = JSON.parse(msg.body);
        // console.log(newMsg)
        setMessages( arr => [...arr, newMsg]);
    };


    return (
        <div className="App">
            <input type="text" ref={newMessage}/>
            <button onClick={send}>send</button>
            <ul>
                {messages.map(message => <li key={message.id}>{message.text}</li>)}
            </ul>
        </div>
);
}

export default Home;
