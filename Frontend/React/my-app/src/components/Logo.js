import styled from 'styled-components';
import { theme } from '../styles/Themes';

export const LogoSection = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
 
  align-items: center;
  background: linear-gradient(45deg, ${theme.colors.primary.white}, ${theme.colors.accent.white});
  color: ${theme.colors.white};
  padding: ${theme.spacing.lg};
  text-align: center;
  background-color: ${(props) => props.theme.colors.white};

  h1 {
    font-size: 4rem;
    font-family: ${theme.fonts.heading};
    margin-bottom: ${theme.spacing.sm};
  }

  p {
    font-size: 1.5rem;
    font-family: ${theme.fonts.primary};
    font-weight: ${theme.fonts.medium};
  }
`;

export default LogoSection;