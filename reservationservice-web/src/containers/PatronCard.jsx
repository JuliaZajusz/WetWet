import React, { Component } from 'react';
import PatronForm from './PatronForm'
import history from '../history'
import { getPatron } from '../clients/PatronClient'
import Button from 'antd/es/button/button'

class PatronCard extends Component {
  state = {
    patron: null,
  };

  componentWillMount = () => {
    if (this.props.history.location.pathname !== '/patron/new') {
      let path = this.props.history.location.pathname.split('/')
      getPatron(path[2]).then((res) => this.setState({ patron: res }))
    }

  }

  render() {
    let arrayPath = this.props.history.location.pathname.split('/');

    return (
      <div className={'margin-md'}>
        {this.props.history.location.pathname === '/patron/new' && <div>
          <PatronForm onSubmitSuccess={(res) => {
            this.setState({ patron: res });
            history.push('/patron/' + res.id)
          }}/>
        </div>}
        {(arrayPath[2] !== 'new' && arrayPath.length <= 3 && this.state.patron) && <div>
          <div className={'flex-between'}>
            <h3>{this.state.patron.firstName} {this.state.patron.lastName}</h3>
            <Button onClick={() => history.push(this.props.history.location.pathname + '/edit')}>Edytuj</Button>
          </div>
          <div>
            {this.state.patron.email}
          </div>
          <div>
            {this.state.patron.phone}
          </div>
          <div>
            Zwierzęta:
            {(this.state.patron && this.state.patron.pets) && this.state.patron.pets.map((pet) => {
              return <div>
                {pet.name}
              </div>
            })}
          </div>
          <div>
            Adresy:
            {(this.state.patron && this.state.patron.addresses) && this.state.patron.addresses.map((address) => {
              return <div>
                {address.street && address.street.name + ' '}

                {address.street && address.houseAppartmentNumber + ', '}
                {address.city && address.city.name + ' '}
                {!address.street && address.houseAppartmentNumber}
              </div>
            })}
          </div>
        </div>}

        {(arrayPath[1] === 'patron'
          && arrayPath[3] === 'edit') && <div>
          <PatronForm data={this.state.patron} onSubmitSuccess={(res) => {
            // this.setState({ patron: res });
            let path = this.props.history.location.pathname.split('/')
            getPatron(path[2]).then((res) => this.setState({ patron: res }))
            history.push('/patron/' + res.id)
          }}/>
        </div>}

      </div>
    )
  }
}

export default PatronCard;
