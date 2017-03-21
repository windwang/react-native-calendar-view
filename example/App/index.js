import React, {Component} from 'react';
import {
    Dimensions,
    ScrollView,
    Text,
    TouchableHighlight,
    View,
    ListView,
    Image
} from 'react-native';

import Calendar from "react-native-calendar-view"
export default class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            // today:new Date(2015,1,2), min:new Date(2015,1,1), max:new Date(2015,1,28)
            fillDefaultColorDates: {
                '2017-03-01': {
                    "color": '#00f0f0',
                    "text": "休假",
                    "textColor": "#000f0f",
                    "textSize":28
                },
                '2017-03-02': {
                    color: '#00f00f',
                    text: "浪去了",
                    textColor: "#0f0f00"
                }
            }

        };

    }

    static propTypes = {}

    static defaultProps = {}

    _changeFillDefaultColorDates() {
        this.setState({
            fillDefaultColorDates: {
                '2017-03-06': {
                    color: '#00f0f0',
                    text: "休假",
                    textColor: "#000f0f"
                },
                '2017-03-07': {
                    color: '#00f00f',
                    text: "浪去了",
                    textColor: "#0f0f00"
                }
            }
        })

    }
    render() {
        console.log("====render", this.state)
        return (
            <View
                style={{
                paddingTop: 20,
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
                <TouchableHighlight onPress={() => this._changeFillDefaultColorDates()}>
                    <Text>Change fillDefaultColorDates</Text>
                </TouchableHighlight>
            </View>
        )
    }
}