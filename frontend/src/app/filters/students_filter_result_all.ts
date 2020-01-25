/**
 * Created by pedro on 02-07-2017.
 */
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'StudentsFilterResultAll',
})
export class StudentsFilterResultAll implements PipeTransform {
  transform(value: any, input: string) {
    if (input) {
      input = input.toLowerCase();
      return value.filter(function (el: any) {
        const pos = el.user.email.indexOf('@');
        const aux = el.user.email.substring(0, pos);
        return aux.toLowerCase().indexOf(input) > -1;
      });
    }
    return value;
  }
}
