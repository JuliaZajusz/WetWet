import React, { Component } from 'react';
import './App.css';
import { getHello } from './clients/HelloClient'
import EmployeesList from './containers/EmloyeesList'
import { Route, Router, Switch } from 'react-router';
import { Icon, Layout, Menu } from 'antd';
import history from './history';
import Timetable from './containers/Timetable'

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
        <div className="App">
          <Layout style={{ minHeight: '100vh' }}>
            <Sider
              collapsible
              collapsed={this.state.collapsed}
              onCollapse={this.onCollapse}
            >
              <div className="logo"/>
              <Menu theme="dark" defaultSelectedKeys={['/calendar']} mode="inline" onClick={(item) => {
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
            </Sider>
            <Layout>

              <Switch>
                <Route exact path='/users' component={EmployeesList}/>
                <Route exact path='/timetable' component={Timetable}/>
                <Route exact path='/patients' component={() =>
                  <Content style={{ margin: '0 16px' }}>
                    <h3 style={{ margin: '16px 0' }}>Mruczek - kot - kot perski</h3>
                    <div style={{ padding: 24, background: '#fff', minHeight: 360 }}>
                      Mruczek is a cat.
                    </div>
                  </Content>}/>
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

export default App;
