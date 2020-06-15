import { Inject, Injectable } from '@angular/core';
import { LOCAL_STORAGE, StorageService } from 'ngx-webstorage-service';

/*
STORAGE_KEY = 'local_todolist';
const currentTodoList = this.storage.get(STORAGE_KEY) || [];
this.storage.set(STORAGE_KEY, currentTodoList);
console.log(this.storage.get(STORAGE_KEY) || 'LocaL storage is empty');
*/

@Injectable()
export class LocalStorageService {
     anotherTodolist = [];
     constructor(@Inject(LOCAL_STORAGE) private storage: StorageService) { }
     public storeOnLocalStorage(taskTitle: string): void {

          // get array of tasks from local storage
          const currentTodoList = this.storage.get('local_todolist') || [];
          // push new task to array
          currentTodoList.push({
              title: taskTitle,
              isChecked: false
          });
          // insert updated array to local storage
          this.storage.set('local_todolist', currentTodoList);
          console.log(this.storage.get('local_todolist') || 'LocaL storage is empty');
     }
}
