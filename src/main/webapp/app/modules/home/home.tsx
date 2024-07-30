import './home.scss';
import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Alert, Form, FormGroup, Label, Input, Button, Row, Col } from 'reactstrap';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { login } from 'app/shared/reducers/authentication';

export const Home = () => {
  const dispatch = useAppDispatch();
  const loginError = useAppSelector(state => state.authentication.loginError);
  const isAuthenticated = useAppSelector(state => state.authentication.isAuthenticated);
  const user = useAppSelector(state => state.authentication.account); 
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
      <Row className="login-box">
        <Col md="6" className="login-image">
          <img src='content/images/Servo.png' alt="Login" />
        </Col>
        <Col md="6" className="login-form">
          <span>
            <h1>Welcome to ServoCRM</h1>
            </span>
          <span style={{color:'black'}}>Please log in to continue</span>
          {loginError && <Alert color="danger">Login failed. Please check your credentials and try again.</Alert>}
          <Form onSubmit={handleLogin}>
            <FormGroup>
             <span>
              <Label for="username">Username</Label>
              </span> 
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
              <span>
                
              <Label for="password">Password</Label>
              </span>
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
              <Label check style={{color:'black'}}>
                <Input
                  type="checkbox"
                  id="rememberMe"
                  name="rememberMe"
                  background-color='black'
                  checked={rememberMe}
                  onChange={e => setRememberMe(e.target.checked)}

                
                />
                Remember me
              </Label>
            </FormGroup>
            <Button color="primary" type="submit">Login</Button>
          </Form>
          <Link to="/account/register" className="alert-link" style={{color:'black'}}>Register a new account</Link>
        </Col>
      </Row>
    </div>
  );
};

export default Home;
