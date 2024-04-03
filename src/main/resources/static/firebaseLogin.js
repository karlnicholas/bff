
import { getAuth, setPersistence, signInWithEmailAndPassword, browserSessionPersistence } from 'https://www.gstatic.com/firebasejs/10.10.0/firebase-auth.js'

const signin = (event) => {
    event.preventDefault(); // Prevent default action of form
    // serviceRequest() // Perform Logic
    let email = document.querySelector("#user_email");
    let password = document.querySelector("#user_password");

    const auth = getAuth();
    setPersistence(auth, browserSessionPersistence)
        .then(() => {
            // Existing and future Auth states are now persisted in the current
            // session only. Closing the window would clear any existing state even
            // if a user forgets to sign out.
            // ...
            // New sign-in will be persisted with session persistence.
            return signInWithEmailAndPassword(auth, email.value, password.value);
        })
        .catch((error) => {
            // Handle Errors here.
            const errorCode = error.code;
            const errorMessage = error.message;
        });
};

document.querySelector('form').addEventListener("submit", signin)
