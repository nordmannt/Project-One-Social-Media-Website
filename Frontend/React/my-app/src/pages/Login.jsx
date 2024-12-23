import { Link } from 'react-router-dom';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import React from 'react';
import axios from 'axios';
import {
  PrimaryButton,
  ContainerPlain,
  Input,
  Title,
  LogoContainer,
  LogoSection,
  FormSection,
  ContainerCard,
} from '../components'; // Assuming all components are properly exported

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/api/auth/login', {
        username,
        password,
      });
      console.log('Login Response:', response.data);
      // Destructure the response data
      const { token, userId } = response.data;

      if (token && userId) {
        // Store token and userId in localStorage
        localStorage.setItem('token', token);
        localStorage.setItem('userId', userId);
        console.log('Login Success: Token and UserId stored');
      } else {
        console.error('Login response missing token or userId');
        setErrorMessage('Login failed. Server did not return the expected data.');
        return;
      }

      // Navigate to the newsfeed after successful login
      navigate('/newsfeed');
    } catch (error) {
      console.error('Login Failed:', error);
      setErrorMessage(
        error.response?.data?.message || 'Login Failed. Please try again.'
      );
    }
  };

  return (
    <ContainerPlain style={{ display: 'flex', height: '100vh' }}>
      {/* Left Column */}
      <LogoSection
        style={{
          flex: 1,
          backgroundColor: '#ffffff',
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
        }}
      >
        <LogoContainer>
          <div>
            <h1 style={{ fontSize: '3rem', color: '#6A5ACD', marginBottom: '0.5rem' }}>Chummy</h1>
            <p style={{ fontSize: '1.5rem', color: '#494949' }}>Just Better!</p>
          </div>
        </LogoContainer>
      </LogoSection>

      {/* Right Column */}
      <FormSection
        style={{
          flex: 1,
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
        }}
      >
        <ContainerCard style={{ width: '100%', maxWidth: '400px' }}>
          <Title style={{ marginBottom: '1rem', textAlign: 'center' }}>Login</Title>
          <form onSubmit={handleSubmit}>
            <Input
              type="text"
              placeholder="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
            <Input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            {errorMessage && (
              <p style={{ color: 'red', marginTop: '0.5rem' }}>{errorMessage}</p>
            )}
            <PrimaryButton type="submit" style={{ marginTop: '1rem' }}>
              Login
            </PrimaryButton>
          </form>

          <p style={{ marginTop: '1rem', textAlign: 'center', color: '#30303d' }}>
            Don't have an account?{' '}
            <Link
              to="/register" // Use 'to' instead of 'href'
              style={{
                color: '#6A5ACD',
                textDecoration: 'underline',
                cursor: 'pointer',
              }}
            >
              Click here to register
            </Link>
          </p>
        </ContainerCard>
      </FormSection>
    </ContainerPlain>
  );
};

export default Login;
