export const theme = {
  // Colors: Retro Meets Postmodern 2025
  colors: {
    primary: "#6A5ACD",          // Soft slate blue (primary highlight color)
    primaryDark: "#4A4E69",      // Dark muted blue for hover/active states
    secondary: "#FF9F1C",        // Retro mustard yellow (accent/secondary buttons)
    secondaryDark: "#D88C00",    // Deeper mustard yellow for hover states
    accent: "#E07A5F",           // Warm terracotta red for accents and highlights
    background: "#F2F2F2",       // Light neutral gray (modern background)
    backgroundDark: "#2F3136",   // Dark muted charcoal for dark mode cards
    text: "#3D3D3D",             // Soft black for primary text
    textLight: "#828282",        // Lighter gray for secondary text
    textDark: "#1A1A1A",         // Bold black for headings/emphasis
    white: "#FFFFFF",            // Standard white for backgrounds or highlights
    black: "#000000",            // Standard black for text/icons
    gray: "#D3D3D3",             // Light gray for borders and subtle backgrounds
    rose: "#F4ACB7",             // Pastel rose pink for notifications or highlights
    roseLight: "#F7CAD0",        // Lighter pastel pink for hover states
    success: "#81B29A",          // Muted teal green for success messages
    warning: "#F2CC8F",          // Pastel orange-yellow for warnings
    info: "#6A994E",             // Earthy olive green for information highlights
    danger: "#D7263D",           // Vibrant retro red for errors
  },

  // Typography
  fonts: {
    primary: "'Roboto', sans-serif",       // Default clean font
    secondary: "'Courier New', monospace", // Retro typewriter font for headings/highlights
    medium: "500",                         // Font weight for normal text
    semibold: "600",                       // Font weight for semi-bold text
    bold: "700",                           // Font weight for bold text
    extraBold: "800",                      // Font weight for extra bold text
    heading: "'Bebas Neue', sans-serif",   // Postmodern bold sans-serif for headings
  },
  
  typography: {
    fontFamily: "'Roboto', sans-serif",
    h1: {
      fontSize: "2.5rem", // Heading 1 size
      fontWeight: "700",
    },
    h2: {
      fontSize: "2rem", // Heading 2 size
      fontWeight: "600",
    },
    body1: {
      fontSize: "1rem",
      fontWeight: "400",
    },
    small: {
      fontSize: "0.875rem",
      fontWeight: "400",
    },
  },

  // Radius (Rounded corners)
  radius: {
    xs: "8px",   // Small radius for buttons/inputs
    sm: "12px",  // Small radius for cards
    md: "16px",  // Medium radius for modals or larger containers
    lg: "20px",  // Extra rounded corners for major highlights
    xl: "24px",
    xxl: "32px",
  },

  // Shadows: Soft and Modern Depth
  shadows: {
    small: "0 1px 3px rgba(0, 0, 0, 0.1)",  
    medium: "0 4px 8px rgba(0, 0, 0, 0.15)", 
    large: "0 8px 16px rgba(0, 0, 0, 0.2)",  
    extraLarge: "0 12px 24px rgba(0, 0, 0, 0.25)", 
  },

  // Spacing (Padding/Margins)
  spacing: {
    xs: "8px",
    sm: "16px",
    md: "24px",
    lg: "32px",
    xl: "48px",
    xxl: "64px",
  },

  // Breakpoints for responsive design
  breakpoints: {
    sm: "576px",
    md: "768px",
    lg: "992px",
    xl: "1200px",
    xxl: "1600px",
  },

  // Transitions
  transitions: {
    fast: "all 0.2s ease-in-out",  
    medium: "all 0.4s ease-in-out", 
    slow: "all 0.6s ease-in-out",   
  },

  // Borders
  borders: {
    thin: "1px solid",     
    thick: "2px solid",    
    radius: "8px",         
    color: "#D3D3D3",      
  },

  // Z-Index
  zIndex: {
    modal: 1000,
    tooltip: 1100,
    dropdown: 1200,
    overlay: 1300,
  },

  // Opacities
  opacities: {
    low: 0.3,  
    medium: 0.6, 
    high: 1,   
  },

  // Layout
  layout: {
    containerMaxWidth: "1200px",
    cardWidth: "320px",
    headerHeight: "60px",
    footerHeight: "50px",
  },

  // Special Effects (Gradients, Hover Effects)
  effects: {
    gradient: "linear-gradient(135deg, #6A5ACD, #FF9F1C)", // Soft purple to mustard yellow gradient
    hoverEffect: "transform: scale(1.03)", // Slight scale-up on hover
  },

  interactive:{
    buttonHover: "#5A4FCF", // Slightly darker shade for hover state
    buttonActive: "#4A3FCF", // Even darker for active state
    linkHover: "#FFAA33",   // Slightly brighter secondary color
    linkActive: "#E07A5F",  // Accent color for active links
  },

  media: {
    sm: "@media (max-width: 576px)", // Small screens
    md: "@media (max-width: 768px)", // Medium screens
    lg: "@media (max-width: 992px)", // Large screens
    xl: "@media (max-width: 1200px)", // Extra large screens
  },

  gradients: {
  primary: "linear-gradient(135deg, #6A5ACD, #4A4E69)", // From primary to dark
  secondary: "linear-gradient(135deg, #FF9F1C, #D88C00)", // Retro mustard yellow
  accent: "linear-gradient(135deg, #E07A5F, #F4ACB7)", // Warm terracotta red to pastel pink
},



  
};
