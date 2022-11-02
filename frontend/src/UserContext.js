import React, {createContext} from 'react';

export const UserContext = createContext({
    currentUser: null,
    toket: null,
    setCurrentUser: () => {},
    setToken: () => {}
});
