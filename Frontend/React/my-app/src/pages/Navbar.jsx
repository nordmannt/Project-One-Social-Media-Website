import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { theme } from "../styles/Themes";
import {  PrimaryButton, SecondaryButton, Input, MainTitle, SmallText, ProfileContainer } from "../components";
import FriendSearch from "./FriendSearch";
const Navbar = ({ userId, profilePicture, onLogout }) => {
  const navigate = useNavigate();

  return (
    <div style={styles.navbar}>
    {/* Top Section: Profile Picture and Logo */}
    <div style={styles.navbarTop}>
      <div
        style={styles.profileContainer}
        onClick={() => navigate(`/profile/${userId}`)}
      >
        <img
          src={profilePicture || "https://via.placeholder.com/50"}
          alt="Profile"
          style={styles.profilePicture}
        />
     </div>
     </div>
  
    {/* Bottom Section: Buttons */}
    <div style={styles.navbarBottom}>
    <MainTitle as="span" style={styles.logo} onClick={() => navigate("/news-feed")}>
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

      <FriendSearch>
        placeholder="Search..."
        style={styles.searchBar}
        onChange={(e) => console.log("Type to search:", e.target.value)}
      </FriendSearch>
      
      <PrimaryButton style={styles.button} onClick={onLogout}>
        Logout
      </PrimaryButton>
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
      navbarBottom: {
        display: "flex", // Arrange buttons in a row
        justifyContent: "center", // Center the buttons
        alignItems: "center",
        gap: theme.spacing.md, // Space between buttons
      },
  navbarLeft: {
    display: "flex",
    alignItems: "center",
  },
  logo: {
    
    fontFamily: theme.fonts.heading,
    fontSize: theme.typography.h1.fontSize,
    color: theme.colors.primary,
    cursor: "pointer",
  },
  navbarCenter: {
    alignItems: "center",
    display: "flex",
    gap: theme.spacing.md,
  },
  link: {
    color: theme.colors.white,
    textDecoration: "none",
    fontSize: theme.typography.body1.fontSize,
  },
  profilePicture: {
    width: "150px",
    height: "150px",
    borderRadius: "50%", // Circular profile picture
    objectFit: "cover", // Ensure picture scales correctly
    marginBottom: theme.spacing.xs,
  },
  navbarRight: {
    display: "flex",
    alignItems: "center",
    gap: theme.spacing.sm,
  },
  searchBar: {
    border: `1px solid ${theme.colors.gray}`,
    borderRadius: theme.radius.md,
    padding: theme.spacing.xs,
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
