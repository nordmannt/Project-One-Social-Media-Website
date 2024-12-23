import styled from 'styled-components';

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