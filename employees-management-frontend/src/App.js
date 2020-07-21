import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import GroupList from './GroupList';
import Home from './Home'
import GroupEdit from './GroupEdit';
class App extends Component{
  render(){
    return(
      <BrowserRouter>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/groups' exact={true} component={GroupList}/>
          <Route path='/groups/:id' component={GroupEdit}/>
        </Switch>
      </BrowserRouter>
    );
  }
}

export default App;
