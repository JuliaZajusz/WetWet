import React, { Component } from 'react';
import { getEmployeesList } from '../clients/EmployeesClient'
import { List } from 'antd'

class EmployeesList extends Component {
  employeesList;

  componentWillMount() {
    this.employeesList = getEmployeesList();
  }

  render() {
    return (
      <div className={'margin-md'}>
        <h3>Lista pracowników</h3>

        <List
          itemLayout="horizontal"
          dataSource={this.employeesList}
          renderItem={item => (
            <List.Item>
              <List.Item.Meta
                title={<a href="https://ant.design">{item.firstName + ' ' + item.lastName}</a>}
                description={item.position}
              />
            </List.Item>
          )}
        />
      </div>
    )
  }

}

export default EmployeesList
