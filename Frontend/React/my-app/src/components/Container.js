import styled from 'styled-components';



import { theme } from '../styles/Themes';

export const NavbarContainer = styled.div`
  width: 100%;                /* Full width */
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: ${theme.spacing.sm};
  background-color: ${theme.colors.white};
  color: ${theme.colors.white};
  box-shadow: ${theme.shadows.small};
  position: relative;         /* Ensures it's in the normal document flow */
  z-index: 1000;              /* Ensures it stays above other elements */
  border-radius: ${theme.radius.lg}; /* Add rounded corners */
   border: 5px solid ${theme.colors.primary}; /* Thick purple border */
  border-radius: ${theme.radius.md}; /* Rounded corners */
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); /* Shadow around the navbar */
`;



export const NewsFeedContainer = styled.div`
  width: 100%;                /* Full width */
  flex: 1;                    /* Allows it to stretch within the parent container */
  padding: ${theme.spacing.lg};
  background-color: ${theme.colors.background.white};
  display: flex;
  flex-direction: column;     /* Stacks content vertically */
  align-items: center;        /* Centers content horizontally */
`;




export const ProfileContainer = styled.div`
  width: 100%;
  padding: ${theme.spacing.lg};
  background-color: ${theme.colors.background};
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;          /* Ensures it spans the full viewport height */
`;





export const Container = styled.div`
  display: flex;
  flex-direction: row;
  height: 100vh;
  background-color: ${(props) => props.theme.colors.background.white};
`;


export const TwoColumnContainer = styled.div`
  display: flex;
  height: 100vh; /* Full viewport height */
  background-color: ${(props) => props.theme.colors.background.white};

  @media (max-width: ${(props) => props.theme.breakpoints.md}) {
    flex-direction: column; /* Stack columns vertically on smaller screens */
  }
`;

export default TwoColumnContainer;


export const ContainerPlain = styled.div`
  display: flex;
  flex-direction: row;
  height: 100vh;
  background-color: ${(props) => props.theme.colors.background.white};

  @media (max-width: ${(props) => props.theme.breakpoints.md}) {
    flex-direction: column;
  background: ${(props) => props.theme.colors.white};
  }
`;

export const ContainerCard = styled(ContainerPlain)`
  box-shadow: ${(props) => props.theme.shadows.medium};
  display: flex;
  height: 35%;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: ${(props) => props.theme.spacing.md}; /* Add padding for spacing */
  width: 100%;
  max-width: 400px; /* Ensure it doesn't expand too much */
  background-color: ${(props) => props.theme.colors.white}; /* Ensure a white background */
  border-radius: ${(props) => props.theme.radius.md}; /* Optional: add rounded corners */
  margin: 0 auto; /* Center horizontally */
`;
/*// Plain Container (no shadow)
export const ContainerPlain = styled.div`
  max-width: 400px;
  margin: 0 auto;
  padding: ${(props) => props.theme.spacing.md};
  border: ${(props) => props.theme.borders.thin} ${(props) => props.theme.colors.white};
  border-radius: ${(props) => props.theme.radius.md};
  background-color: ${(props) => props.theme.colors.white};
`;

// Card Container (with shadow)
export const ContainerCard = styled(ContainerPlain)`
  box-shadow: ${(props) => props.theme.shadows.medium};
`;
*/
// Fluid Container (full width)
export const ContainerFluid = styled.div`
  width: 100%;
  margin: 0;
  padding: ${(props) => props.theme.spacing.sm};
  background-color: ${(props) => props.theme.colors.background};
`;



export const LogoContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 300px; /* Adjust as needed */
  height: 200px; /* Adjust as needed */
  background: ${(props) => props.theme.colors.white};
  color: ${(props) => props.theme.colors.primaryDark};
  border-radius: 50%; /* Creates an oval shape */
  box-shadow: ${(props) => props.theme.shadows.medium};
  padding: ${(props) => props.theme.spacing.sm};
  margin: ${(props) => props.theme.spacing.md};
`;



/*import styled from 'styled-components';
import { theme } from '../styles/Themes';

export const Container = styled.div`
  max-width: 400px;
  margin: ${theme.spacing.md};
  border: ${theme.borders.thin} ${theme.borders.color};
  border-radius: ${theme.radius.md};
  box-shadow: ${theme.shadows.medium};
  background-color: ${theme.colors.white};
`;
*/