import styled from 'styled-components';

export const FormSection = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center vertically */
  align-items: center; /* Center horizontally */
  padding: ${(props) => props.theme.spacing.lg};
  

  @media (max-width: ${(props) => props.theme.breakpoints.md}) {
    padding: ${(props) => props.theme.spacing.md};
  }
`;

export default FormSection;
