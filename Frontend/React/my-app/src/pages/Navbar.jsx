import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { theme } from "../styles/Themes";
import {  PrimaryButton, SecondaryButton, Input, MainTitle, SmallText } from "../components";

const Navbar = ({ userId, onLogout }) => {
  const navigate = useNavigate();

  return (
    <div style={styles.navbar}>
      {/* Logo Area */}
      <div style={styles.navbarLeft}>
        <MainTitle a= "span" style={styles.logo} onClick={() => navigate("/news-feed")}>
          Chummy
        </MainTitle>
      </div>

      {/* Navigation Links */}
      <div style={styles.navbarCenter}>
        
        <Link to="/news-feed" style={styles.link}>
        <SmallText>News Feed</SmallText></Link>
          
        <Link to={`/profile/${userId}`} style={styles.link}><SmallText>Profile</SmallText></Link>
             
        <Link to="/friends-list" style={styles.link}><SmallText>Friends</SmallText></Link>
              
        <Link to="/notifications" style={styles.link}><SmallText>Notifications</SmallText></Link>
        
        <Link to={`/profile/${userId}`} style={styles.link}><SmallText>Profile</SmallText></Link>
        </div>

      {/* User Actions */}
      <div style={styles.navbarRight}>
        <Input
          placeholder="Search..."
          style={styles.searchBar}
          onChange={(e) => console.log("Type to search:", e.target.value)}
        />
        <SecondaryButton style={styles.button} onClick={() => navigate(`/profile/${userId}`)}>
          Profile
        </SecondaryButton>
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
    display: "flex",
    gap: theme.spacing.md,
  },
  link: {
    color: theme.colors.white,
    textDecoration: "none",
    fontSize: theme.typography.body1.fontSize,
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
