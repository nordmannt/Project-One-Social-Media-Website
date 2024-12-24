import styled from 'styled-components';
import { theme } from '../styles/Themes';

// Base Button
export const ButtonBase = styled.button`
  border: none;
  border-radius: ${(props) => props.theme.radius.sm};
  cursor: pointer;
  font-family: ${(props) => props.theme.fonts.primary};
  padding: ${(props) => props.size || '10px 16px'};
  font-size: ${(props) => props.fontSize || '1rem'};
  transition: ${(props) => props.theme.transitions.fast};
`;

// Primary Button
export const PrimaryButton = styled(ButtonBase)`
  background-color: ${(props) => props.theme.colors.primary};
  color: ${(props) => props.theme.colors.white};
 border: ${(props) => props.theme.borders.thick}
  &:hover {
    background-color: ${(props) => props.theme.colors.primaryDark};
  }
`;

// Secondary Button
export const SecondaryButton = styled(ButtonBase)`
  background-color: transparent;
  color: ${(props) => props.theme.colors.primary};
  border: ${(props) => props.theme.borders.thin} ${(props) => props.theme.colors.primary};

  &:hover {
    background-color: ${(props) => props.theme.colors.primary};
    color: ${(props) => props.theme.colors.white};
  }
`;

// Danger Button
export const DangerButton = styled(ButtonBase)`
  background-color: ${(props) => props.theme.colors.danger};
  color: ${(props) => props.theme.colors.white};

  &:hover {
    background-color: ${(props) => props.theme.colors.secondaryDark};
  }
`;





/*import styled from 'styled-components';
import { theme } from '../styles/Themes';

export const Button = styled.button`
  background-color: ${(props) => props.theme.colors.primary};
  color: ${(props) => props.theme.colors.white};
  border-radius: ${(props) => props.theme.radius.sm};
  padding: ${(props) => props.theme.spacing.sm};
  transition: ${(props) => props.theme.transitions.fast};

  &:hover {
    background-color: ${(props) => props.theme.colors.primaryDark};
    transform: scale(1.05);
  }
`;
*/