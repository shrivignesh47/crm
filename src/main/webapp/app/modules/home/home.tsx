import './home.scss';
import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Alert, Form, FormGroup, Label, Input, Button } from 'reactstrap';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { login } from 'app/shared/reducers/authentication';

export const Home = () => {
  const dispatch = useAppDispatch();
  const loginError = useAppSelector(state => state.authentication.loginError);
  const isAuthenticated = useAppSelector(state => state.authentication.isAuthenticated);
  const user = useAppSelector(state => state.authentication.account); // Assuming account contains user details including roles
  const navigate = useNavigate();

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [rememberMe, setRememberMe] = useState(false);

  const handleLogin = (event) => {
    event.preventDefault();
    dispatch(login(username, password, rememberMe));
  };

  useEffect(() => {
    if (isAuthenticated && user) {
      if (user.authorities.includes('ROLE_ADMIN')) {
        navigate('/admin'); // Navigate to admin page
      } else {
        navigate('/dashboard'); // Navigate to user dashboard
      }
    }
  }, [isAuthenticated, user, navigate]);

  return (
    <div className="login-container">
      <div className="login-box">
        <h1>Welcome to ServoCRM</h1>
        <p>Please log in to continue</p>
        {loginError && <Alert color="danger">Login failed. Please check your credentials and try again.</Alert>}
        <Form onSubmit={handleLogin}>
          <FormGroup>
            <Label for="username">Username</Label>
            <Input
              type="text"
              id="username"
              name="username"
              value={username}
              onChange={e => setUsername(e.target.value)}
              required
            />
          </FormGroup>
          <FormGroup>
            <Label for="password">Password</Label>
            <Input
              type="password"
              id="password"
              name="password"
              value={password}
              onChange={e => setPassword(e.target.value)}
              required
            />
          </FormGroup>
          <FormGroup check className="form-check">
            <Label check>
              <Input
                type="checkbox"
                id="rememberMe"
                name="rememberMe"
                checked={rememberMe}
                onChange={e => setRememberMe(e.target.checked)}
              />
              Remember me
            </Label>
          </FormGroup>
          <Button color="primary" type="submit">Login</Button>
        </Form>
        <Link to="/account/register" className="alert-link">Register a new account</Link>
      </div>
    </div>
  );
};

export default Home;
