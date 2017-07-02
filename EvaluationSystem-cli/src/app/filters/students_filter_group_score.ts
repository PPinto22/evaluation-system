/**
 * Created by pedro on 02-07-2017.
 */
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'StudentsFilterGroupScore',
})
export class StudentsFilterGroupScore implements PipeTransform {
  transform(value: any, input: string) {
    if (input) {
      input = input.toLowerCase();
      return value.filter(function (el: any) {
        const pos = el.email.indexOf('@');
        const aux = el.email.substring(0, pos);
        return aux.toLowerCase().indexOf(input) > -1;
      });
    }
    return value;
  }
}
