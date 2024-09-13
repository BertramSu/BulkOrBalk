import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

const ItemEdit = () => {
    const initialFormState = {
      name: '',
      purchaseDate: '',
      balk: false
    };
    const [group, setGroup] = useState(initialFormState);
    const navigate = useNavigate();
    const { id } = useParams();
  
    useEffect(() => {
      if (id !== 'new') {
        fetch(`/api/item/${id}`)
          .then(response => response.json())
          .then(data => setGroup(data));
      }
    }, [id]);
  
    const handleChange = (event) => {
      const { name, value, type, checked } = event.target;
      const updatedValue = type === 'checkbox' ? checked : value;
      setGroup({ ...group, [name]: updatedValue });
    };
  
    const handleSubmit = async (event) => {
      event.preventDefault();
  
      await fetch(`/api/item${group.id ? `/${group.id}` : ''}`, {
        method: group.id ? 'PUT' : 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(group)
      });
      setGroup(initialFormState);
      navigate('/items');
    };
  
    const title = <h2>{group.id ? 'Edit Item' : 'Add Item'}</h2>;
  
    return (
      <div>
        <AppNavbar />
        <Container>
          {title}
          <Form onSubmit={handleSubmit}>
            <FormGroup>
              <Label for="name">Name</Label>
              <Input type="text" name="name" id="name" value={group.name || ''}
                     onChange={handleChange} autoComplete="name"/>
            </FormGroup>
            <FormGroup check>
              <Label check>
                <Input type="checkbox" name="balk" id="balk" checked={group.balk || false}
                       onChange={handleChange} />
                Balk
              </Label>
            </FormGroup>
            <FormGroup>
                <Label for="purchaseDate">Purchase Date and Time</Label>
                <Input type="datetime-local" name="purchaseDate" id="purchaseDate" 
                    value={group.purchaseDate || ''} onChange={handleChange} 
                    autoComplete="off" />
            </FormGroup>
            <FormGroup>
              <Button color="primary" type="submit">Save</Button>{' '}
              <Button color="secondary" tag={Link} to="/items">Cancel</Button>
            </FormGroup>
          </Form>
        </Container>
      </div>
    );
  };
  
export default ItemEdit;