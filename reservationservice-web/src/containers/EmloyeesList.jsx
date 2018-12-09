import React, { Component } from 'react';
import { getEmployeesList } from '../clients/EmployeesClient'
import { Button, List } from 'antd'
import history from '../history'

class EmployeesList extends Component {
  constructor() {
    super();
    this.state = {
      data: [],
    }
  }

  componentWillMount() {
    getEmployeesList()
      .then(res => this.setState({ data: res }))
  }

  render() {
    return (
      <div className={'margin-md'}>
        <div className={'flex-between'}>
          <h3>Lista pracowników</h3>
          <Button onClick={() => this.addEmployee()}>Dodaj</Button>
        </div>
        <List
          itemLayout="horizontal"
          dataSource={this.state.data}
          renderItem={item => (
            <List.Item>
              <List.Item.Meta
                title={<a
                  onClick={() => history.push('/employee/' + item.id)}>{item.firstName + ' ' + item.lastName}</a>}
                description={item.position}
              />
            </List.Item>
          )}
        />
      </div>
    )
  }

  addEmployee() {

  }
}

export default EmployeesList
