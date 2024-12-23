import styled from 'styled-components';
import { theme } from '../styles/Themes';

export const Input = styled.input`
  display: block;
  width: 100%;
  margin-bottom: ${theme.spacing.sm};
  padding: ${theme.spacing.xs};
  border: ${theme.borders.thin} ${theme.colors.gray};
  border-radius: ${theme.radius.sm};
  font-family: ${theme.fonts.primary};
`;

export const Textarea = styled.textarea`
  display: block;
  width: 100%;
  margin-bottom: ${theme.spacing.sm};
  padding: ${theme.spacing.xs};
  border: ${theme.borders.thin} ${theme.colors.gray};
  border-radius: ${theme.radius.sm};
  font-family: ${theme.fonts.primary};
  resize: vertical;
  min-height: 100px;
  font-size: 1rem;
  line-height: 1.5;

  &:focus {
    outline: none;
    border-color: ${theme.colors.primary};
    box-shadow: 0 0 4px ${theme.colors.primaryLight};
  }
`;
