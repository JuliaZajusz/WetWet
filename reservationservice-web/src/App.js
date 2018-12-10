import React, { Component } from 'react';
import './App.css';
import { getHello } from './clients/HelloClient'
import { Route, Router, Switch } from 'react-router';
import { Layout, Menu } from 'antd';
import history from './history';
import AuthorizedContent from './AuthorizedContent'
import SignIn from './containers/SignIn'
import PrivateRoute from './PrivateRoute'

const { Header, Content, Footer, Sider } = Layout;

const SubMenu = Menu.SubMenu;

class App extends Component {

  getText() {
    getHello().then((res) => {
      this.setState({text: res})})
  }

  state = {
    collapsed: false,
    text: 'tu pojawi się tekst jeśli klikniesz przycisk',
  };

  onCollapse = (collapsed) => {
    console.log(collapsed);
    this.setState({ collapsed });
  }

  render() {
    return (
      <Router history={history}>
              <Switch>
                <Route exact path='/login' component={SignIn}/>
                <PrivateRoute path='/' component={AuthorizedContent}/>
              </Switch>
      </Router>
    );
  }
}

export default App;
