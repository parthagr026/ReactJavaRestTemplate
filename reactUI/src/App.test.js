import React from 'react';
import { render } from '@testing-library/react';
import App from './App';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';


test('renders learn react link', () => {
    const { getByText } = render( < App / > );
    const linkElement = getByText(/learn react/i);
    expect(linkElement).toBeInTheDocument();
});