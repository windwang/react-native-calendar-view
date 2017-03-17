import React, {Component} from 'react';
import {
    Dimensions,
    ScrollView,
    Text,
    TouchableHighlight,
    View,
    ListView,
    Image,
} from 'react-native';

import Calendar from "react-native-calendar-view"
export default class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            // today:new Date(2015,1,2),
            // min:new Date(2015,1,1),
            // max:new Date(2015,1,28)
            fillDefaultColorDates:{'2017-03-01':'#00ff00','2017-03-02':'#0000ff'}
        };

    }

    static propTypes = {}

    static defaultProps = {}

    render() {
        console.log("====render",this.state)
        return (
            <View  style={{
            paddingTop:20,
            alignSelf: 'center'
        }}>
           <Calendar            
            height={280}
            tileHeight={35}
            hideHeader={false}
            today={this.state.today}
            selectedDates={this.state.dates}
            dateBounds={[this.state.min, this.state.max]}
            fillDefaultColorDates={this.state.fillDefaultColorDates}
            onSelectDate={date => {
            console.log(date);
        }}
            onMonthChange={month => {
            console.log(month);
        }}/>
        <Text>12312312x</Text>
        </View>
        )
    }
}