import styled from 'styled-components';
import { theme } from '../styles/Themes';

/* Main Title (h1) */
export const Title = styled.h1`
  font-size: 2rem;
  font-weight: ${theme.fonts.bold};
  color: ${theme.colors.primary};
  text-align: center;
  margin-bottom: ${theme.spacing.sm};
`;

/* Subtitle (h2) */
export const Subtitle = styled.h2`
  font-size: 1.5rem;
  font-weight: ${theme.fonts.semibold};
  color: ${theme.colors.textDark};
  margin-bottom: ${theme.spacing.xs};
`;

/* Regular Text (p) */
export const Text = styled.p`
  font-size: 1rem;
  color: ${theme.colors.text};
  line-height: 1.6;
  margin-bottom: ${theme.spacing.sm};
`;

/* Small Text */
export const SmallText = styled.p`
  font-size: 0.875rem;
  color: ${theme.colors.textLight};
  line-height: 1.4;
`;

/* Highlighted Text */
export const HighlightedText = styled.span`
  color: ${theme.colors.accent};
  font-weight: ${theme.fonts.semibold};
`;
