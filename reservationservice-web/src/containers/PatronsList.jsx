import React, { Component } from 'react';
import { Button, Table } from 'antd';
import { getPatrons } from '../clients/PatronClient'
import history from '../history'

class PatronsList extends Component {

  constructor() {
    super();
    this.state = {
      data: [],
    }
  }

  componentWillMount() {
    getPatrons().then(res => this.setState({ data: res }))
  }

  columns = [{
    title: 'Imię',
    dataIndex: 'firstName',
    key: 'firstName',
    // render: text => <a href="">{text}</a>
  }, {
    title: 'Nazwisko',
    dataIndex: 'lastName',
    key: 'lastName',
  }, {
    title: 'Telefon',
    dataIndex: 'phone',
    key: 'phone',
  },
    {
      title: 'Email',
      dataIndex: 'email',
      key: 'email',
    },
    {
      title: 'Zwierzęta',
      dataIndex: 'pets',
      key: 'patients',
      render: pets => <div>{pets.map((pet) => <span><a href={'/patient/' + pet.id}>{pet.name}</a><br/></span>)}</div>,
    }];


  render() {
    const data = this.state.data;
    return (
      <div className={'margin-md'}>
        <div className={'flex-between'}>
          <h3>Lista opiekunów</h3>
          <Button onClick={() => this.addPatron()}>Dodaj</Button>
        </div>
        <Table dataSource={data}
               columns={this.columns}
               rowKey='id'
               size="medium"
               onRowClick={(patron) => history.push('/patron/' + patron.id)}
        />
      </div>
    )
  }

  addPatron() {
    history.push('patron/new');
  }
}

export default PatronsList
