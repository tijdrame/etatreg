import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBp3Infos } from 'app/shared/model/bp-3-infos.model';
import { Bp3InfosService } from './bp-3-infos.service';

@Component({
  templateUrl: './bp-3-infos-delete-dialog.component.html',
})
export class Bp3InfosDeleteDialogComponent {
  bp3Infos?: IBp3Infos;

  constructor(protected bp3InfosService: Bp3InfosService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bp3InfosService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bp3InfosListModification');
      this.activeModal.close();
    });
  }
}
