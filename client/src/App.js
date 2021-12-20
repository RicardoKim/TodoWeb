import React from 'react';
import Todo from './components/Todo';
import {Paper, List, Container} from "@material-ui/core";
import AddTodo from './components/AddTodo';
import './App.css';
import { call } from './service/ApiService';

class App extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      items : [],
    };
  }
  
  add = (item) => {
    const requestOptions = {
      method : 'POST',
      headers : {'Content-Type' : 'application/json'},
      body : JSON.stringify(item)
    };
    fetch('http://localhost:5000/todo', requestOptions)
    .then(response => response.json())
    .then(response => this.setState({items : response.data}));
  }

  delete = (item) => {
    const requestOptions ={
      method: 'DELETE',
      headers : {'Content-Type' : 'application/json'},
      body : JSON.stringify(item)
    };
    fetch('http://localhost:5000/todo', requestOptions)
    .then(response => response.json())
    .then(response => this.setState({items : response.data}));
  }

  componentDidMount() {
    call("/todo", "GET", null).then((response) =>
    this.setState({items: response.data}))
  }
  
  render(){
    console.log(this.state.items);
    var todoItems = this.state.items.length > 0 && (
      <Paper style = {{margin : 16}}>
        <List>
          {this.state.items.map((item, idx) => (
            <Todo item = {item} key = {item.id} delete = {this.delete}/>

          ))}
        </List>
      </Paper>
    );
    return (
      <div className='App'>
        <Container maxWidth = "md">
          <AddTodo add = {this.add}/>
          <div className = "TodoList">{todoItems}</div>
        </Container>
      </div>
    )
  }
 
}

export default App;
