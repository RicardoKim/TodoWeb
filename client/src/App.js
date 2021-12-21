import React from 'react';
import Todo from './components/Todo';
import {Paper, List, Container, Grid, Button, AppBar, Toolbar, Typography} from "@material-ui/core";
import AddTodo from './components/AddTodo';
import './App.css';
import { call, signout } from './service/ApiService';

class App extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      items : [],
      loading : true,
    };
  }
  
  add = (item) => {
    call("/todo", "POST", item).then((response) =>
    this.setState({items : response.data})
    );
  };

  delete = (item) => {
    call("/todo", "DELETE", item).then((response)=>
    this.setState({items : response.data})
    );
  }

  componentDidMount() {
    call("/todo", "GET", null).then((response) =>
    this.setState({items: response.data, loading : false}))
  }
  
  update = (item) => {
    call("/todo", "PUT", item).then((response) => 
    this.setState({items : response.data}));
  }

  render(){
    var todoItems = this.state.items.length > 0 && (
      <Paper style = {{margin : 16}}>
        <List>
          {this.state.items.map((item, idx) => (
            <Todo item = {item} key = {item.id} delete = {this.delete} update = {this.update}/>

          ))}
        </List>
      </Paper>
    );
    var navigatorBar = (
      <AppBar position = "static">
        <Toolbar>
          <Grid justify = "space-between" container>
            <Grid item>
              <Typography variant = "h6">오늘의 할일</Typography>
            </Grid>
          </Grid>
          <Grid>
            <Button color = "inherit" onClick = {signout}>로그아웃</Button>
          </Grid>
        </Toolbar>
      </AppBar>
    )
    
    var todoListPage = (
      <div>
        {navigatorBar}
        <Container maxWidth = "md">
          <AddTodo add = {this.add}>
            <div className = "TodoList">{todoItems}</div>
          </AddTodo>
        </Container>
      </div>
    )
    return (
      <div className='App'>
        {navigatorBar}
        <Container maxWidth = "md">
          <AddTodo add = {this.add}/>
          <div className = "TodoList">{todoItems}</div>
        </Container>
      </div>
    )
  }
 
}

export default App;
