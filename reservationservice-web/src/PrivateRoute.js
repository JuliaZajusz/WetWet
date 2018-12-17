import React, { Component } from 'react'
import { Redirect, Route } from 'react-router';

class PrivateRoute extends Component {

  render() {
    const { component: Component, ...rest } = this.props;
    return <Route {...rest} render={(props) => (
      localStorage.getItem('TOKEN')
        ? <Component {...props}/>
        : <Redirect to='/login'/>
    )}/>
  }
}

export default PrivateRoute
