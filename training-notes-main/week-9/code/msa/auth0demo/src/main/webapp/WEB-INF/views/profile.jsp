<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
</head>
<body>
    <h2>My Account</h2>
    <p><strong>Email:</strong> ${profile['email']}</p>
	<p><strong>Name:</strong>${profile['nickname']}</p>
	<p><strong>Picture:</strong>${profile['picture']}</p>
</body>
<!--<script>
    document.addEventListener("DOMContentLoaded", function() {
        // Check if the token exists in localStorage
        const token = localStorage.getItem('jwtToken');

        if (token) {
            // Display user's email or relevant content
            const email = decodeJwt(token).email; // Function to decode and get email from token
            document.getElementById('userEmail').innerText = email; // Update your profile section
        }
    });

    function decodeJwt(token) {
        const payload = token.split('.')[1];
        return JSON.parse(window.atob(payload)); // Decode the base64 URL encoded string
    }
</script>-->

</html>
