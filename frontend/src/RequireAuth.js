import {
    Navigate,
} from "react-router-dom";
import { useContext } from 'react';
import {UserContext} from "./UserContext";

function RequireAuth({children}) {
    const auth = useContext(UserContext);

    if (!auth.currentUser) {
        return <Navigate to="/login" />
    }

    return children;
};

export default RequireAuth;


