import React, { Component } from 'react';
// import './AuthorizedContent.css';
import { getHello } from './clients/HelloClient'
import EmployeesList from './containers/EmloyeesList'
import PatientsList from './containers/PatientsList'
import { Route, Router, Switch } from 'react-router';
import { Icon, Layout, Menu } from 'antd';
import history from './history';
import Timetable from './containers/Timetable'
import PatronsList from './containers/PatronsList'
import PatronCard from './containers/PatronCard'
import SignUp from './containers/SignUp'
import { signOut } from './clients/AuthorizationClient'

const { Header, Content, Footer, Sider } = Layout;

const SubMenu = Menu.SubMenu;

class AuthorizedContent extends Component {

  getText() {
    getHello().then((res) => {
      this.setState({ text: res })
    })
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
        <div className="AuthorizedContent">
          <Layout style={{ minHeight: '100vh' }}>
            <Sider
              collapsible
              collapsed={this.state.collapsed}
              onCollapse={this.onCollapse}
            >
              <div className="logo"/>
              <Menu theme="dark" defaultSelectedKeys={['/patients']} mode="inline" onClick={(item) => {
                history.push(item.key)
              }}>
                <Menu.Item key="/patients">
                  <Icon type="smile"/>
                  <span>Pacjenci</span>
                </Menu.Item>
                <Menu.Item key="/patrons">
                  <Icon type="user"/>
                  <span>Opiekuni</span>
                </Menu.Item>
                <Menu.Item key="/timetable">
                  <Icon type="calendar"/>
                  <span>Terminarz</span>
                </Menu.Item>
                <Menu.Item key="/users">
                  <Icon type="team"/>
                  <span>Użytkownicy</span>
                </Menu.Item>
              </Menu>
              <Menu theme="dark" mode="inline" onClick={() => {
                signOut();
              }}>
                <Menu.Item key="/login">
                  <Icon type="lock"/>
                  <span>Wyloguj</span>
                </Menu.Item>
              </Menu>
            </Sider>
            <Layout>

              <Switch>
                <Route exact path='/users' component={EmployeesList}/>
                <Route exact path='/patients' component={PatientsList}/>
                <Route exact path='/timetable' component={Timetable}/>
                <Route exact path='/patrons' component={PatronsList}/>
                <Route path='/patron/:id' component={PatronCard}/>
                <Route exact path='/users/add' component={SignUp}/>
                <Route component={() => <div>
                  <button onClick={() => this.getText()}>get text</button>
                  <div>{this.state.text}</div>
                </div>}/>
              </Switch>
            </Layout>
          </Layout>
        </div>
      </Router>
    );
  }
}

export default AuthorizedContent;
