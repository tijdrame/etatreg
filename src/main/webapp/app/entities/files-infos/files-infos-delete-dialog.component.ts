import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFilesInfos } from 'app/shared/model/files-infos.model';
import { FilesInfosService } from './files-infos.service';

@Component({
  templateUrl: './files-infos-delete-dialog.component.html',
})
export class FilesInfosDeleteDialogComponent {
  filesInfos?: IFilesInfos;

  constructor(
    protected filesInfosService: FilesInfosService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.filesInfosService.delete(id).subscribe(() => {
      this.eventManager.broadcast('filesInfosListModification');
      this.activeModal.close();
    });
  }
}
