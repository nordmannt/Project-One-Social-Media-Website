import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { theme } from "../styles/Themes";
import { PrimaryButton, MainTitle, SmallText } from "../components";
import FriendSearch from "./FriendSearch";

const Navbar = ({ onLogout }) => {
  const [loggedInProfilePicture, setLoggedInProfilePicture] = useState(null); // Store the logged-in user's profile picture
  const navigate = useNavigate();
  const userId = localStorage.getItem("userId");

  useEffect(() => {
    const fetchLoggedInUserProfile = async () => {
      try {
        const token = localStorage.getItem("token");
        const response = await axios.get(
          `http://localhost:8080/api/auth/profile/${userId}`, // Fetch the logged-in user's profile
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );
        setLoggedInProfilePicture(response.data.avatarUrl); // Set the profile picture in state
      } catch (error) {
        console.error("Error fetching logged-in user's profile:", error);
      }
    };

    if (userId) {
      fetchLoggedInUserProfile();
    }
  }, [userId]);

  return (
    <div style={styles.navbar}>
      {/* Top Section: Profile Picture and Logo */}
      <div style={styles.navbarTop}>
        <div
          style={styles.profileContainer}
          onClick={() => navigate(`/profile/${userId}`)}
        >
          <img
            src={loggedInProfilePicture || "https://via.placeholder.com/50"}
            alt="Profile"
            style={styles.profilePicture}
          />
        </div>
      </div>

      {/* Bottom Section: Buttons */}
      <div style={styles.navbarBottom}>
        <MainTitle
          as="span"
          style={styles.logo}
          onClick={() => navigate("/newsfeed")}
        >
          Chummy
        </MainTitle>
        <Link to={`/profile/${userId}`} style={styles.link}>
          <SmallText>Profile</SmallText>
        </Link>
        <Link to="/friends-list" style={styles.link}>
          <SmallText>Friends</SmallText>
        </Link>
        <Link to="/notifications" style={styles.link}>
          <SmallText>Notifications</SmallText>
        </Link>
        <FriendSearch style={styles.searchBar} />
        <button style={styles.button} onClick={onLogout}>
          Logout
        </button>
      </div>
    </div>
  );
};

const styles = {
  navbar: {
    position: "relative", // Ensure it's in the document flow
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    padding: `${theme.spacing.sm} ${theme.spacing.lg}`,
    backgroundColor: theme.colors.white,
    color: theme.colors.white,
    width: "100%", // Ensure it spans the full width
  },
  navbarTop: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    marginBottom: "20px",
  },
  profilePicture: {
    width: "95px",
    height: "95px",
    borderRadius: "50%", // Circular profile picture
    objectFit: "cover", // Ensure picture scales correctly
    border: "5px solid #654321",
  },
  navbarBottom: {
    display: "flex", // Arrange buttons in a row
    justifyContent: "center", // Center the buttons
    alignItems: "center",
    gap: theme.spacing.md, // Space between buttons
  },
  logo: {
    fontFamily: theme.fonts.heading,
    fontSize: theme.typography.h1.fontSize,
    color: theme.colors.primary,
    cursor: "pointer",
  },
  link: {
    color: theme.colors.primaryDark,
    textDecoration: "none",
    fontSize: theme.typography.body1.fontSize,
  },
  searchBar: {
    border: `1px solid ${theme.colors.gray}`,
    borderRadius: theme.radius.md,
    padding: theme.spacing.xs,
    outline: "none", // Removes focus outline
    boxShadow: "none", // Removes focus shadow
  },
  button: {
    backgroundColor: theme.colors.secondary,
    color: theme.colors.white,
    border: "none",
    borderRadius: theme.radius.sm,
    cursor: "pointer",
    padding: theme.spacing.xs,
    transition: theme.transitions.fast,
  },
};

export default Navbar;
