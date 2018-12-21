import React, { Component } from 'react';
// import './AuthorizedContent.css';
import EmployeesList from './containers/EmloyeesList'
import PatientsList from './containers/PatientsList'
import { Route, Router, Switch } from 'react-router';
import { Icon, Layout, Menu } from 'antd';
import history from './history';
import Timetable from './containers/Timetable'
import PatronsList from './containers/PatronsList'
import PatronCard from './containers/PatronCard'
import SignUp from './containers/SignUp'
import { getPositionFromToken, signOut } from './clients/AuthorizationClient'
import PatientCard from './containers/PatientCard'
import ConsultingRoomInaccessibilityList from './containers/ConsultingRoomInaccessibilityList'
import ConsultingRoomInaccessibilityForm from './containers/ConsultingRoomInaccessibilityForm'

const { Header, Content, Footer, Sider } = Layout;

const SubMenu = Menu.SubMenu;

class AuthorizedContent extends Component {

  componentWillMount() {
    getPositionFromToken().then((res) => {
      this.setState({ role: res.data.type.toUpperCase() })
    })
  }

  state = {
    collapsed: false,
    role: 'NONE',
  };

  onCollapse = (collapsed) => {
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
              <Menu theme="dark" mode="inline" onClick={(item) =>
                history.push(item.key)
              }>
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
                <Menu.Item key="/consultingRoomInaccessibility">
                  <Icon type="table"/>
                  <span>Niedostępność gabinetów</span>
                </Menu.Item>
                {this.state.role !== 'NONE' &&
                <Menu.Item key="/users">
                  <Icon type="team"/>
                  <span>Użytkownicy</span>
                </Menu.Item>}
              </Menu>
              <Menu theme="dark" mode="inline" onClick={() =>
                signOut()
              }>
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
                <Route path='/timetable' component={Timetable}/>
                <Route exact path='/consultingRoomInaccessibility' component={ConsultingRoomInaccessibilityList}/>
                <Route exact path='/patrons' component={PatronsList}/>
                <Route path='/patron/:id' component={PatronCard}/>
                <Route path='/patient/:id' component={PatientCard}/>
                <Route exact path='/consultingRoomInaccessibility/:id' component={ConsultingRoomInaccessibilityForm}/>
                <Route exact path='/users/add' component={SignUp}/>
              </Switch>
            </Layout>
          </Layout>
        </div>
      </Router>
    );
  }
}

export default AuthorizedContent;
