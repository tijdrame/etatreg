import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBp4Infos } from 'app/shared/model/bp-4-infos.model';
import { Bp4InfosService } from './bp-4-infos.service';

@Component({
  templateUrl: './bp-4-infos-delete-dialog.component.html',
})
export class Bp4InfosDeleteDialogComponent {
  bp4Infos?: IBp4Infos;

  constructor(protected bp4InfosService: Bp4InfosService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bp4InfosService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bp4InfosListModification');
      this.activeModal.close();
    });
  }
}
