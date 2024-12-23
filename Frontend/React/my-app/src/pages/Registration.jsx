import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import {
  PrimaryButton,
  ContainerCard,
  ContainerPlain,
  Input,
  MainTitle,
} from '../components';


const Register = () => {
  const [formData, setFormData] = useState({
    
    username: '',
    firstName: '',
    lastName: '',
    password: '',
    confirmPassword: '',
  });

  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      setErrorMessage("Passwords don't match");
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/api/auth/register',{
       
        username: formData.username,
        firstName: formData.firstName,
        lastName: formData.lastName,
        password: formData.password,
      });
      console.log('Registration Success:', response.data);
      navigate('/login'); // Redirect to login page
    } catch (error) {
      setErrorMessage(error.response?.data?.message || 'Registration failed. Please try again.');
    }
  };

  return (
    <ContainerCard>
      <MainTitle>Register</MainTitle>
      <form onSubmit={handleSubmit}>
        <Input
          type="text"
          name="username"
          placeholder="username"
          value={formData.username}
          onChange={handleChange}
          required
        />
        <Input
          type="text"
          name="firstName"
          placeholder="First Name"
          value={formData.firstName}
          onChange={handleChange}
          required
        />
        <Input
          type="text"
          name="lastName"
          placeholder="Last Name"
          value={formData.lastName}
          onChange={handleChange}
          required
        />
        
        <Input
          type="password"
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleChange}
          required
        />
        <Input
          type="password"
          name="confirmPassword"
          placeholder="Confirm Password"
          value={formData.confirmPassword}
          onChange={handleChange}
          required
        />
        {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
        <PrimaryButton type="submit">Register</PrimaryButton>
      </form>
      <p style={{ marginTop: '1rem', textAlign: 'center', color: '#3D3D3D' }}>
        Already have an account?{' '}
        <a
          href="/login"
          style={{ color: '#6A5ACD', textDecoration: 'underline', cursor: 'pointer' }}
        >
          Login here
        </a>
      </p>
    </ContainerCard>
  );
};

export default Register;
