import {initializeApp} from "https://www.gstatic.com/firebasejs/10.10.0/firebase-app.js"
import { getAuth, signInWithEmailAndPassword, signOut } from "https://www.gstatic.com/firebasejs/10.10.0/firebase-auth.js"

// TODO: Replace the following with your app's Firebase project configuration
// See: https://firebase.google.com/docs/web/learn-more#config-object
const firebaseConfig = {
    apiKey: "AIzaSyCongxkpulsmb76B_XPecNzEqOUtREHHh4",
    authDomain: "merchloans.firebaseapp.com",
};
// Initialize Firebase
initializeApp(firebaseConfig);
const auth = getAuth();

auth.onAuthStateChanged(function(user) {
    document.getElementById("login-form").reset();
    document.getElementById("login-form").style.display="none";
    document.getElementById("login-error").textContent = '';
    if (user) {
        callAPI(user)
    } else {
        document.getElementById("uid").textContent = "";
        document.getElementById("principal").textContent = "";
    }
});

async function callAPI(user) {
    const token = await user.getIdToken()
    const res = await fetch(window.location.origin + "/api/answer?uid="+user.uid, {
        headers: {authorization: `Bearer ${token}`}
    })
    const answer = await res.json();
    document.getElementById("uid").textContent = "Uid = " +answer.uid;
    document.getElementById("principal").textContent = "Principal = " +answer.principal;
}

const signin = (event) => {
    event.preventDefault(); // Prevent default action of form
    // serviceRequest() // Perform Logic
    let email = document.getElementById("user_email").value;
    let password = document.getElementById("user_password").value;
    signInWithEmailAndPassword(auth, email, password)
        .catch((error) => {
            document.getElementById("login-error").textContent = error.message;
        });
};

const signout = (event) => {
    event.preventDefault(); // Prevent default action of form
    // serviceRequest() // Perform Logic
    document.getElementById("login-form").style.display="none";
    signOut(auth);
};

const showform = (event) => {
    event.preventDefault(); // Prevent default action of form
    document.getElementById("login-form").style.display="block";
};

document.getElementById('login-form').addEventListener("submit", signin);
document.getElementById('login-link').addEventListener("click", showform);
document.getElementById('logout-link').addEventListener("click", signout);
