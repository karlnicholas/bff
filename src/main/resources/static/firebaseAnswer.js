import { getAuth} from 'https://www.gstatic.com/firebasejs/10.10.0/firebase-auth.js'

getAuth().onAuthStateChanged(function(user) {
    if (user) {
        // User is signed in.
        console.log("logged in");
        callAPI(user)
    } else {
        console.log("NOT logged in");
        // No user is signed in.
    }
});

async function callAPI(user) {
    if (user) {
        const token = await user.getIdToken()
        const res = await fetch("http://localhost:8080/api/answer?uid="+user.uid, {
            headers: {authorization: `Bearer ${token}`}
        })
        const answer = await res.json();
        document.querySelector("#uid").textContent = "Uid = " +answer.uid;
        document.querySelector("#principal").textContent = "Principal = " +answer.principal;
    } else {
        console.log("No user is logged in")
    }
}